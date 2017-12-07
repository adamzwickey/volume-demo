package io.pivotal.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping("/path")
    @ResponseBody
    public String info() {
        return storageService.config();
    }

    @RequestMapping("/files")
    @ResponseBody
    public List listUploadedFiles() throws IOException {

        return storageService.loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList());
    }

    @RequestMapping("/file")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@RequestParam String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map handleFileUpload(MultipartHttpServletRequest request) {
        Map result = new HashMap();
        Iterator i = request.getFileMap().entrySet().iterator();
        List files = new ArrayList();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            String fileName = (String) me.getKey();
            MultipartFile multipartFile = (MultipartFile) me.getValue();
            storageService.store(multipartFile);

            Map f = new HashMap();
            f.put("name", "");
            f.put("size", "");
            f.put("url", "");
            f.put("thumbnailUrl", "");
            f.put("deleteUrl", "");
            f.put("deletetype", "DELETE");
            files.add(f);
        }
        result.put("files", files);
        return result;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }


    @Autowired
    private RestTemplate _template;

    @RequestMapping(value = "/sleuth")
    @ResponseBody
    public List sleuth(String url) {
        return _template.getForObject(url, List.class);
    }

}