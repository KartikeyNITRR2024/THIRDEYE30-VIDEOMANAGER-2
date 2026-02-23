package com.thirdeye3_2.video.manager.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thirdeye3_2.video.manager.dtos.MultiMediaResponseDto;
import com.thirdeye3_2.video.manager.dtos.MultiMediaUploadDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.MultiMediaService;

@RestController
@RequestMapping("/vm2/multimedia")
public class MultiMediaController {

	@Autowired
	private MultiMediaService multiMediaService;
	
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<MultiMediaResponseDto> uploadFile(@ModelAttribute MultiMediaUploadDto uploadDto) {
        return new Response<>(true, 0, null, multiMediaService.uploadMultiMedia(uploadDto));
    }
	
	@GetMapping("/view/{key}")
    public ResponseEntity<Resource> viewFile(@PathVariable("key") UUID key) {
		MultiMediaResponseDto metadata = multiMediaService.getMultiMediaDetails(key);
        byte[] fileData = multiMediaService.downloadMultiMediaContent(key);
        ByteArrayResource resource = new ByteArrayResource(fileData);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(metadata.getMultiMediaType()))
                .contentLength(metadata.getSize()) 
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + metadata.getName() + "\"")
                .body(resource);
    }
	
	@GetMapping("/info/{key}")
    public Response<MultiMediaResponseDto> getFileInfo(@PathVariable("key") UUID key) {
        return new Response<>(true, 0, null, multiMediaService.getMultiMediaDetails(key));
    }
	
	@DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
		multiMediaService.deleteMultiMedia(id);
        return new Response<>(true, 0, null, "Multimedia deleted successfully");
    }
}
