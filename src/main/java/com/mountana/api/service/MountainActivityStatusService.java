package com.mountana.api.service;

import com.mountana.api.constant.Constant;
import com.mountana.api.data.entity.MountainActivityStatus;
import com.mountana.api.data.repository.MountainActivityStatusRepository;
import com.mountana.api.dto.MountainActivityStatusDTO;
import com.mountana.api.model.WebAPIResponseModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MountainActivityStatusService {
    private final MountainActivityStatusRepository mountainActivityStatusRepository;
    private final ModelMapper modelMapper;

    public Iterable<MountainActivityStatus> getAll(){
        return mountainActivityStatusRepository.findAllByIsDeleted(false);
    }

    public MountainActivityStatus getById(UUID mountainActivityStatusId){
        Optional<MountainActivityStatus> mountainActivityStatus = mountainActivityStatusRepository.findByIdAndIsDeleted(mountainActivityStatusId, false);
        return mountainActivityStatus.orElse(null);
    }

    public void insert(MountainActivityStatusDTO mountainActivityStatusDTO){
        MountainActivityStatus mountainActivityStatus = new MountainActivityStatus();
        modelMapper.map(mountainActivityStatusDTO, mountainActivityStatus);
        mountainActivityStatus.setCreatedBy(Constant.SYSTEM_UPDATED_BY);
        mountainActivityStatusRepository.save(mountainActivityStatus);
    }

    public WebAPIResponseModel<?> update(UUID mountainActivityStatusId, MountainActivityStatusDTO mountainActivityStatusDTO){
        WebAPIResponseModel<?> responseModel = new WebAPIResponseModel<>();
        Optional<MountainActivityStatus> mountainActivityStatus = mountainActivityStatusRepository.findById(mountainActivityStatusId);
        if(mountainActivityStatus.isPresent()){
            MountainActivityStatus mountainActivityStat = mountainActivityStatus.get();
            modelMapper.map(mountainActivityStatusDTO, mountainActivityStat);
            mountainActivityStat.setUpdatedBy(Constant.SYSTEM_UPDATED_BY);

            mountainActivityStatusRepository.save(mountainActivityStat);
            responseModel.setSuccess("Success update mountain activity status!");

        }else{
            responseModel.setError("Data mountain activity status not found!");
        }
        return responseModel;
    }

    public void delete(UUID mountainActivityStatusId){
        Optional<MountainActivityStatus> mountainActivityStatus = mountainActivityStatusRepository.findById(mountainActivityStatusId);
        mountainActivityStatus.ifPresent(x -> {
            x.setDeleted(true);
            x.setUpdatedBy(Constant.SYSTEM_UPDATED_BY);
            mountainActivityStatusRepository.save(x);
        });
    }
}
