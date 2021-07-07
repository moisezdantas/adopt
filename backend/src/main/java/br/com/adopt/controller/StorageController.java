package br.com.adopt.controller;

import br.com.adopt.components.Storage;
import br.com.adopt.controller.util.ApiUtilOperation;
import br.com.adopt.controller.util.SecuredUtil;
import br.com.adopt.dto.ImageDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private Storage storage;

    @ApiOperation(value = "Image upload")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ApiUtilOperation.MESSAGE_200),
            @ApiResponse(code = 400, message = ApiUtilOperation.MESSAGE_400),
            @ApiResponse(code = 401, message = ApiUtilOperation.MESSAGE_401),
            @ApiResponse(code = 403, message = ApiUtilOperation.MESSAGE_403),
            @ApiResponse(code = 404, message = ApiUtilOperation.MESSAGE_404),
            @ApiResponse(code = 500, message = ApiUtilOperation.MESSAGE_500),
    })
    @PostMapping(value = "upload")
    @Secured({SecuredUtil.ROLE_ADMIN, SecuredUtil.ROLE_USER})
    public ResponseEntity<ImageDTO> upload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            ImageDTO dto =  storage.minioUpload(multipartFile);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @ApiOperation(value = "Get image")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ApiUtilOperation.MESSAGE_200),
            @ApiResponse(code = 400, message = ApiUtilOperation.MESSAGE_400),
            @ApiResponse(code = 401, message = ApiUtilOperation.MESSAGE_401),
            @ApiResponse(code = 403, message = ApiUtilOperation.MESSAGE_403),
            @ApiResponse(code = 404, message = ApiUtilOperation.MESSAGE_404),
            @ApiResponse(code = 500, message = ApiUtilOperation.MESSAGE_500),
    })
    @GetMapping(value = "/view/**")
    @Secured({SecuredUtil.ROLE_ADMIN, SecuredUtil.ROLE_USER})
    public void view(HttpServletRequest request, HttpServletResponse response) {
        String imgPath = extractPathFromPattern(request);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            response.setContentType("image/jpeg;charset=utf-8");
            inputStream = storage.getFileInputStream(imgPath);
            outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            response.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException("Preview picture failed" + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }

    /**
     * Truncate all strings after the specified URL as parameters
     * This is to prevent the problem of not being matched when the URL contains Chinese or special characters (/ etc.)
     *
     * @param request
     * @return
     */
    private static String extractPathFromPattern(final HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }
}
