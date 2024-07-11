package com.mountana.api.controller;

import com.mountana.api.data.entity.HikingRoute;
import com.mountana.api.dto.HikingRouteDTO;
import com.mountana.api.model.WebAPIResponseModel;
import com.mountana.api.service.HikingRouteService;
import com.mountana.api.utility.FormValidationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/hiking-route")
@RequiredArgsConstructor
public class HikingRouteController {
    private final HikingRouteService hikingRouteService;
    private final FormValidationUtil formValidationUtil = new FormValidationUtil();

    @GetMapping
    public ResponseEntity<WebAPIResponseModel<Iterable<HikingRoute>>> getAllHikingRoute(){
        try{
            return ResponseEntity.ok(new WebAPIResponseModel<Iterable<HikingRoute>>().setSuccess(hikingRouteService.getAll()));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{hikingRouteId}")
    public ResponseEntity<WebAPIResponseModel<HikingRoute>> getHikingRouteById(@PathVariable UUID hikingRouteId){
        try{
            return ResponseEntity.ok(new WebAPIResponseModel<HikingRoute>().setSuccess(hikingRouteService.getById(hikingRouteId)));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<WebAPIResponseModel<?>> insertHikingRoute(@Valid @RequestBody HikingRouteDTO hikingRouteDTO,
                                                              Errors errors){
        try{
            WebAPIResponseModel<?> responseModel = new WebAPIResponseModel<>();
            if(errors.hasErrors()) {
                return ResponseEntity.badRequest().body(responseModel.setError(formValidationUtil.buildErrorMessage(errors)));
            }

            hikingRouteService.insert(hikingRouteDTO);
            return ResponseEntity.ok(responseModel.setSuccess("Success insert new hiking route!"));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{hikingRouteId}")
    public ResponseEntity<WebAPIResponseModel<?>> updateHikingRoute(@PathVariable UUID hikingRouteId,
                                                              @Valid @RequestBody HikingRouteDTO hikingRouteDTO,
                                                              Errors errors){
        try{
            if(errors.hasErrors()) {
                return ResponseEntity.badRequest().body(new WebAPIResponseModel<>().setError(formValidationUtil.buildErrorMessage(errors)));
            }

            return ResponseEntity.ok(hikingRouteService.update(hikingRouteId, hikingRouteDTO));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{hikingRouteId}")
    public ResponseEntity<WebAPIResponseModel<?>> deleteHikingRouteById(@PathVariable UUID hikingRouteId){
        try{
            hikingRouteService.delete(hikingRouteId);
            return ResponseEntity.ok(new WebAPIResponseModel<>().setSuccess("Success delete hiking route!"));
        }catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
