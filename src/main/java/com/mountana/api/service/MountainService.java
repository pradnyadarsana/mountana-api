package com.mountana.api.service;

import com.mountana.api.constant.Constant;
import com.mountana.api.data.entity.Mountain;
import com.mountana.api.data.repository.MountainRepository;
import com.mountana.api.dto.MountainDTO;
import com.mountana.api.model.WebAPIResponseModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MountainService {
    private final MountainRepository mountainRepository;
    private final ModelMapper modelMapper;

    public Iterable<Mountain> getAll(){
        return mountainRepository.findAllByIsDeleted(false);
    }

    public Mountain getById(UUID mountainId){
        Optional<Mountain> mountain = mountainRepository.findByIdAndIsDeleted(mountainId, false);
        return mountain.orElse(null);
    }

    public void insert(MountainDTO mountainDTO){
        Mountain mountain = new Mountain();
        modelMapper.map(mountainDTO, mountain);
        mountain.setCreatedBy(Constant.SYSTEM_UPDATED_BY);
        mountainRepository.save(mountain);
    }

    public WebAPIResponseModel<?> update(UUID mountainId, MountainDTO mountainDTO){
        WebAPIResponseModel<?> responseModel = new WebAPIResponseModel<>();
        Optional<Mountain> mountain = mountainRepository.findById(mountainId);
        if(mountain.isPresent()){
            Mountain updatedMountain = mountain.get();
            modelMapper.map(mountainDTO, updatedMountain);
            updatedMountain.setUpdatedBy(Constant.SYSTEM_UPDATED_BY);

            mountainRepository.save(updatedMountain);
            responseModel.setSuccess("Success update mountain!");

        }else{
            responseModel.setError("Data mountain not found!");
        }
        return responseModel;
    }

    public void delete(UUID mountainId){
        Optional<Mountain> mountain = mountainRepository.findById(mountainId);
        mountain.ifPresent(x -> {
            x.setDeleted(true);
            x.setUpdatedBy(Constant.SYSTEM_UPDATED_BY);
            mountainRepository.save(x);
        });
    }
}
