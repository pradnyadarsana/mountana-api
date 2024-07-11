package com.mountana.api.controller;

import com.mountana.api.dto.MountainActivityStatusDTO;
import com.mountana.api.model.WebAPIResponseModel;
import com.mountana.api.service.MountainActivityStatusService;
import com.mountana.api.utility.FormValidationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/mountain-activity-status")
@RequiredArgsConstructor
public class MountainActivityStatusController {
    private final MountainActivityStatusService mountainActivityStatusService;
    private final FormValidationUtil formValidationUtil = new FormValidationUtil();

    @GetMapping
    public ResponseEntity<WebAPIResponseModel<?>> getAllMountainActivityStatus(){
        try{
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess(mountainActivityStatusService.getAll()));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{mountainActivityStatusId}")
    public ResponseEntity<WebAPIResponseModel<?>> getMountainActivityStatusById(@PathVariable UUID mountainActivityStatusId){
        try{
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess(mountainActivityStatusService.getById(mountainActivityStatusId)));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<WebAPIResponseModel<?>> insertMountainActivityStatus(@Valid @RequestBody MountainActivityStatusDTO mountainActivityStatusDTO,
                                                              Errors errors){
        try{
            WebAPIResponseModel<?> responseModel = new WebAPIResponseModel<>();
            if(errors.hasErrors()) {
                return ResponseEntity.badRequest().body(responseModel.setError(formValidationUtil.buildErrorMessage(errors)));
            }

            mountainActivityStatusService.insert(mountainActivityStatusDTO);
            return ResponseEntity.ok(responseModel.setSuccess("Success insert new mountain activity status!"));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{mountainActivityStatusId}")
    public ResponseEntity<WebAPIResponseModel<?>> updateMountainActivityStatus(@PathVariable UUID mountainActivityStatusId,
                                                              @Valid @RequestBody MountainActivityStatusDTO mountainActivityStatusDTO,
                                                              Errors errors){
        try{
            if(errors.hasErrors()) {
                return ResponseEntity.badRequest().body(new WebAPIResponseModel<>().setError(formValidationUtil.buildErrorMessage(errors)));
            }

            return ResponseEntity.ok(mountainActivityStatusService.update(mountainActivityStatusId, mountainActivityStatusDTO));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{mountainActivityStatusId}")
    public ResponseEntity<WebAPIResponseModel<?>> deleteMountainActivityStatusById(@PathVariable UUID mountainActivityStatusId){
        try{
            mountainActivityStatusService.delete(mountainActivityStatusId);
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess("Success delete mountain activity status!"));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
