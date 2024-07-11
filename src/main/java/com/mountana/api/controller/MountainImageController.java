package com.mountana.api.controller;

import com.mountana.api.dto.UploadMountainImageDTO;
import com.mountana.api.model.WebAPIResponseModel;
import com.mountana.api.service.MountainImageService;
import com.mountana.api.utility.FormValidationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/mountain-image")
@RequiredArgsConstructor
public class MountainImageController {
    private final MountainImageService mountainImageService;
    private final FormValidationUtil formValidationUtil = new FormValidationUtil();

    @GetMapping
    public ResponseEntity<WebAPIResponseModel<?>> getAllMountainImages(){
        try{
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess(mountainImageService.getAll()));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/id/{mountainImageId}")
    public ResponseEntity<WebAPIResponseModel<?>> getMountainImageById(@PathVariable UUID mountainImageId){
        try{
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess(mountainImageService.getById(mountainImageId)));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/mountain-id/{mountainId}")
    public ResponseEntity<WebAPIResponseModel<?>> getMountainImagesByMountainId(@PathVariable UUID mountainId){
        try{
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess(mountainImageService.getByMountainId(mountainId)));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<WebAPIResponseModel<?>> uploadMountainImage(@Valid @ModelAttribute UploadMountainImageDTO uploadMountainImageDTO,
                                                                   Errors errors){
        try{
            WebAPIResponseModel<?> responseModel = new WebAPIResponseModel<>();
            if(errors.hasErrors()) {
                return ResponseEntity.badRequest().body(responseModel.setError(formValidationUtil.buildErrorMessage(errors)));
            }

            mountainImageService.saveImages(uploadMountainImageDTO);
            return ResponseEntity.ok(responseModel.setSuccess("Success save mountain images!"));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/id/{mountainImageId}")
    public ResponseEntity<WebAPIResponseModel<?>> deleteMountainImageById(@PathVariable UUID mountainImageId){
        try{
            mountainImageService.deleteImageById(mountainImageId);
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess("Success delete mountain image!"));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/mountain-id/{mountainId}")
    public ResponseEntity<WebAPIResponseModel<?>> deleteMountainImageByMountainId(@PathVariable UUID mountainId){
        try{
            mountainImageService.deleteImagesByMountainId(mountainId);
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess("Success delete mountain images!"));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
