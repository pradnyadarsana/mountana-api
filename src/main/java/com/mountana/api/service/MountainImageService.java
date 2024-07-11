package com.mountana.api.service;

import com.mountana.api.config.ExternalServicesProperties;
import com.mountana.api.constant.AmazonS3Constant;
import com.mountana.api.constant.Constant;
import com.mountana.api.data.entity.MountainImage;
import com.mountana.api.data.repository.MountainImageRepository;
import com.mountana.api.dto.UploadMountainImageDTO;
import com.mountana.api.utility.AmazonS3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MountainImageService {
    private final MountainImageRepository mountainImageRepository;
    private final AmazonS3Util amazonS3Util = new AmazonS3Util();
    private final ExternalServicesProperties externalServicesProperties;

    public Iterable<MountainImage> getAll(){
        return mountainImageRepository.findAllByIsDeleted(false);
    }

    public MountainImage getById(UUID mountainImageId){
        Optional<MountainImage> mountainImage = mountainImageRepository.findByIdAndIsDeleted(mountainImageId, false);
        return mountainImage.orElse(null);
    }

    public Iterable<MountainImage> getByMountainId(UUID mountainId){
        return mountainImageRepository.findAllByMountainIdAndIsDeleted(mountainId, false);
    }

    public void saveImages(UploadMountainImageDTO uploadMountainImageDTO) throws IOException, InterruptedException {
        if(uploadMountainImageDTO!=null && uploadMountainImageDTO.getImages()!=null) {
            List<MountainImage> mountainImageList = new ArrayList<>();

            for (MultipartFile image: uploadMountainImageDTO.getImages()
                 ) {
                String folderInS3 = AmazonS3Constant.MOUNTAIN_IMAGE_FOLDER_PATH;
                String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                amazonS3Util.MultipartUpload(folderInS3, fileName, image.getInputStream());

                String imageUrl = externalServicesProperties.getImageAssetsUrl() + folderInS3 + "/" + fileName;
                MountainImage mountainImage = new MountainImage();
                mountainImage.setMountainId(uploadMountainImageDTO.getMountainId());
                mountainImage.setImageUrl(imageUrl);
                mountainImage.setCreatedBy(Constant.SYSTEM_UPDATED_BY);
                mountainImageList.add(mountainImage);
            }

            mountainImageRepository.saveAll(mountainImageList);
        }
    }

    public void deleteImageById(UUID mountainImageId){
        Optional<MountainImage> mountainImage = mountainImageRepository.findByIdAndIsDeleted(mountainImageId, false);
        mountainImage.ifPresent(x -> {
            x.setDeleted(true);
            x.setUpdatedBy(Constant.SYSTEM_UPDATED_BY);
            mountainImageRepository.save(x);
        });
    }

    public void deleteImagesByMountainId(UUID mountainId){
        Iterable<MountainImage> mountainImages = mountainImageRepository.findAllByMountainIdAndIsDeleted(mountainId, false);
        mountainImages.forEach(x -> {
            x.setDeleted(true);
            x.setUpdatedBy(Constant.SYSTEM_UPDATED_BY);
        });
        mountainImageRepository.saveAll(mountainImages);
    }
}
