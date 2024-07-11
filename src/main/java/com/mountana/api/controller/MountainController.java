package com.mountana.api.controller;

import com.mountana.api.dto.MountainDTO;
import com.mountana.api.model.WebAPIResponseModel;
import com.mountana.api.service.MountainService;
import com.mountana.api.utility.FormValidationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/mountain")
@RequiredArgsConstructor
public class MountainController {
    private final MountainService mountainService;
    private final FormValidationUtil formValidationUtil = new FormValidationUtil();

    @GetMapping
    public ResponseEntity<WebAPIResponseModel<?>> getAllMountain(){
        try{
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess(mountainService.getAll()));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{mountainId}")
    public ResponseEntity<WebAPIResponseModel<?>> getMountainById(@PathVariable UUID mountainId){
        try{
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess(mountainService.getById(mountainId)));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<WebAPIResponseModel<?>> insertMountain(@Valid @RequestBody MountainDTO mountainModel,
                                                              Errors errors){
        try{
            WebAPIResponseModel<?> responseModel = new WebAPIResponseModel<>();
            if(errors.hasErrors()) {
                return ResponseEntity.badRequest().body(responseModel.setError(formValidationUtil.buildErrorMessage(errors)));
            }

            mountainService.insert(mountainModel);
            return ResponseEntity.ok(responseModel.setSuccess("Success insert new mountain!"));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{mountainId}")
    public ResponseEntity<WebAPIResponseModel<?>> updateMountain(@PathVariable UUID mountainId,
                                                              @Valid @RequestBody MountainDTO mountainModel,
                                                              Errors errors){
        try{
            if(errors.hasErrors()) {
                return ResponseEntity.badRequest().body(new WebAPIResponseModel<>().setError(formValidationUtil.buildErrorMessage(errors)));
            }

            return ResponseEntity.ok(mountainService.update(mountainId, mountainModel));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{mountainId}")
    public ResponseEntity<WebAPIResponseModel<?>> deleteMountainById(@PathVariable UUID mountainId){
        try{
            mountainService.delete(mountainId);
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess("Success delete mountain!"));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
