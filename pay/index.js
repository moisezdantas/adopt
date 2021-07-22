require("dotenv").config();
const express = require("express");
var path = require("path");
var ejs = require("ejs");
var partials = require("express-partials");
const paypal = require("paypal-rest-sdk");
var axios = require("axios");
const { api } = require("./src/services/api");

const app = express();

app.use(express.json());
app.use(partials());
app.use(express.urlencoded({ extended: false }));
app.set("views", path.join(__dirname, "/src/views"));
app.set("view engine", "ejs");

// Configuração do paypal

paypal.configure({
  mode: process.env.MODE,
  client_id: process.env.CLIENT_ID,
  client_secret: process.env.CLIENT_SECRET,
});

app.get("/", (req, res) => {
  const { price, description, userId, token } = req.query;

  const newPrice = price.replace(".", "");

  res.render("index", {
    price: newPrice,
    description,
    userId,
    token,
  });
});

app.post("/paypal", (req, res) => {
  const { description, price, userId, token } = req.body;

  const total = price * 1;

  const totalNew = parseFloat(total) / 100;
  const priceNew = parseFloat(price) / 100;

  const currency = "BRL";

  var pay = {
    intent: "sale",
    payer: {
      payment_method: "paypal",
    },
    redirect_urls: {
      return_url: `http://192.168.0.11:3001/success?userId=${userId}&total=${total}&description=${description}&token=${token}`,
      cancel_url: "http://192.168.0.11:3001/cancel",
    },
    transactions: [
      {
        item_list: {
          items: [
            {
              name: description,
              sku: description,
              price: priceNew,
              currency,
              quantity: 1,
            },
          ],
        },
        amount: {
          currency,
          total: totalNew,
        },
        description: "Doação para abrigo de animais",
      },
    ],
  };

  paypal.payment.create(pay, (error, payment) => {
    if (error) {
      console.log(`Erro ao criar pagamento ${error}`);
    } else {
      for (let i = 0; i < payment.links.length; i++) {
        const p = payment.links[i];

        if (p.rel === "approval_url") {
          res.redirect(p.href);
        }
      }
    }
  });
});

app.get("/success", (req, res) => {
  const payerId = req.query.PayerID;
  const paymentId = req.query.paymentId;
  const userId = req.query.userId;
  const total = req.query.total;
  const description = req.query.description;
  const token = req.query.token;

  const totalNew = parseFloat(total) / 100;

  const final = {
    payer_id: payerId,
    transactions: [
      {
        amount: {
          currency: "BRL",
          total: totalNew,
        },
      },
    ],
  };

  paypal.payment.execute(paymentId, final, (error, payment) => {
    if (error) {
      console.log(`Erro ao finalizar ${error}`);
    } else {
      const data = {
        name: "Doação",
        sku: "Doação",
        description: description,
        price: totalNew,
        payerID: payerId,
        paymentId: paymentId,
        user: {
          id: userId,
        },
      };
      addPayment(data, token).catch((error) => console.log("erro", error));

      res.render("success", {
        id: payment.id,
        email: payment.payer.payer_info.email,
        total: totalNew,
      });
    }
  });
});

app.get("cancel", (req, res) => {
  res.render("cancel");
});

async function addPayment(data, token) {
  await api.post(
    "/order",
    { ...data },
    {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        Authorization: `Bearer ${token}`,
      },
    }
  );
}

app.listen(3001, () => {
  console.log("Running!");
});
