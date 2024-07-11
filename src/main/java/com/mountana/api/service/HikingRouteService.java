package com.mountana.api.service;

import com.mountana.api.constant.Constant;
import com.mountana.api.data.entity.HikingRoute;
import com.mountana.api.data.repository.HikingRouteRepository;
import com.mountana.api.dto.HikingRouteDTO;
import com.mountana.api.model.WebAPIResponseModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HikingRouteService {
    private final HikingRouteRepository hikingRouteRepository;
    private final ModelMapper modelMapper;

    public Iterable<HikingRoute> getAll(){
        return hikingRouteRepository.findAllByIsDeleted(false);
    }

    public HikingRoute getById(UUID hikingRouteId){
        Optional<HikingRoute> hikingRoute = hikingRouteRepository.findByIdAndIsDeleted(hikingRouteId, false);
        return hikingRoute.orElse(null);
    }

    public void insert(HikingRouteDTO hikingRouteDTO){
        HikingRoute hikingRoute = new HikingRoute();
        modelMapper.map(hikingRouteDTO, hikingRoute);
        hikingRoute.setCreatedBy(Constant.SYSTEM_UPDATED_BY);
        hikingRouteRepository.save(hikingRoute);
    }

    public WebAPIResponseModel<?> update(UUID hikingRouteId, HikingRouteDTO hikingRouteDTO){
        WebAPIResponseModel<?> responseModel = new WebAPIResponseModel<>();
        Optional<HikingRoute> hikingRoute = hikingRouteRepository.findById(hikingRouteId);
        if(hikingRoute.isPresent()){
            HikingRoute route = hikingRoute.get();
            modelMapper.map(hikingRouteDTO, route);
            route.setUpdatedBy(Constant.SYSTEM_UPDATED_BY);

            hikingRouteRepository.save(route);
            responseModel.setSuccess("Success update hiking route!");

        }else{
            responseModel.setError("Data hiking route not found!");
        }
        return responseModel;
    }

    public void delete(UUID hikingRouteId){
        Optional<HikingRoute> hikingRoute = hikingRouteRepository.findById(hikingRouteId);
        hikingRoute.ifPresent(x -> {
            x.setDeleted(true);
            x.setUpdatedBy(Constant.SYSTEM_UPDATED_BY);
            hikingRouteRepository.save(x);
        });
    }
}
