package com.ryazangrove.anomaly_finder_backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ryazangrove.anomaly_finder_backend.exceptions.ImageInfoNotFoundException;
import com.ryazangrove.anomaly_finder_backend.exceptions.ImageNotFoundException;
import com.ryazangrove.anomaly_finder_backend.models.ImageInfo;
import com.ryazangrove.anomaly_finder_backend.models.ImageInfo.TargetArea;
import com.ryazangrove.anomaly_finder_backend.repository.ImageInfoRepository;

@SpringBootTest
public class ImageInfoServiceTest {
    
    @Mock
    private ImageInfoRepository imageInfoRepository;

    @InjectMocks
    private ImageInfoService imageInfoService;

    @Test
    public void shouldSaveImageInfo() {
        TargetArea targetArea = TargetArea.builder()
			.xMin(580).xMax(643)
			.yMin(428).yMax(506)
			.build();
        ImageInfo imageInfo = ImageInfo.builder()
			.fileName("1")
			.type("jpg")
			.imageWidth(2208)
			.imageHeight(1400)
			.target(targetArea)
			.build();

        imageInfoService.saveImageInfo(imageInfo);

        verify(imageInfoRepository, times(1)).save(imageInfo);
    }

    @Test
    public void shouldReturnImageInfoById() {
        Long imageInfoId = 100l;
        TargetArea targetArea = TargetArea.builder()
            .xMin(580).xMax(643)
            .yMin(428).yMax(506)
            .build();
        ImageInfo imageInfo = ImageInfo.builder()
            .fileName("1")
            .type("jpg")
            .imageWidth(2208)
            .imageHeight(1400)
            .target(targetArea)
            .build();
        when(imageInfoRepository.findById(imageInfoId)).thenReturn(Optional.of(imageInfo));

        ImageInfo info = imageInfoService.getImageInfo(imageInfoId);

        verify(imageInfoRepository, times(1)).findById(imageInfoId);
        assertEquals(imageInfo, info);
    }

    @Test
    public void shouldThrowExceptionWhenImageInfoByIdNotFound() {
        Long imageInfoId = 100l;
        when(imageInfoRepository.findById(imageInfoId)).thenThrow(new ImageInfoNotFoundException(imageInfoId));

        try{
            imageInfoService.getImageInfo(imageInfoId);
        } catch (ImageInfoNotFoundException e) {
            assertEquals("Could not find metadata for image with id " + imageInfoId, e.getMessage());
        }

        verify(imageInfoRepository, times(1)).findById(imageInfoId);
    }

    @Test
    public void shouldThrowExceptionWhenImageInfoByImageFileNameNotFound() {
        String imageFileName = "123";
        when(imageInfoRepository.findByFileName(imageFileName)).thenThrow(new ImageInfoNotFoundException(imageFileName));

        try{
            imageInfoService.loadImage(imageFileName);
        } catch (ImageInfoNotFoundException e) {
            assertEquals("Could not find metadata for image with file name " + imageFileName, e.getMessage());
        }

        verify(imageInfoRepository, times(1)).findByFileName(imageFileName);
    }

    @Test
    public void shouldThrowExceptionWhenImageByImageFileNameNotFound() {
        String imageFileName = "123";
        TargetArea targetArea = TargetArea.builder()
            .xMin(580).xMax(643)
            .yMin(428).yMax(506)
            .build();
        ImageInfo imageInfo = ImageInfo.builder()
            .fileName("do_not_exist")
            .type("jpg")
            .imageWidth(2208)
            .imageHeight(1400)
            .target(targetArea)
            .build();
        when(imageInfoRepository.findByFileName(imageFileName)).thenReturn(Optional.of(imageInfo));

        try{
            imageInfoService.loadImage(imageFileName);
        } catch (ImageNotFoundException e) {
            assertEquals("Could not find image with file name " + imageFileName, e.getMessage());
        }

        verify(imageInfoRepository, times(1)).findByFileName(imageFileName);
    }

    @Test
    public void shouldReturnInputStreamOfFoundedImage() {
        String imageFileName = "1";
        TargetArea targetArea = TargetArea.builder()
            .xMin(580).xMax(643)
            .yMin(428).yMax(506)
            .build();
        ImageInfo imageInfo = ImageInfo.builder()
            .fileName("1")
            .type("jpg")
            .imageWidth(2208)
            .imageHeight(1400)
            .target(targetArea)
            .build();
        when(imageInfoRepository.findByFileName(imageFileName)).thenReturn(Optional.of(imageInfo));

        
        InputStream stream = imageInfoService.loadImage(imageFileName);
        
        verify(imageInfoRepository, times(1)).findByFileName(imageFileName);
        assertNotNull(stream);
    }

    @Test
    public void shouldReturnRandomSequenceOfImageInfos() {
        TargetArea target1 = TargetArea.builder()
            .xMin(580).xMax(643)
            .yMin(428).yMax(506)
            .build();
        ImageInfo imageInfo1 = ImageInfo.builder()
            .fileName("1")
            .type("jpg")
            .imageWidth(2208)
            .imageHeight(1400)
            .target(target1)
            .build();
        TargetArea target2 = TargetArea.builder()
            .xMin(162).xMax(196)
            .yMin(145).yMax(192)
            .build();
        ImageInfo imageInfo2 = ImageInfo.builder()
            .fileName("2")
            .type("png")
            .imageWidth(1024)
            .imageHeight(768)
            .target(target2)
            .build();

        List<ImageInfo> sequence = new ArrayList<ImageInfo>();
        sequence.add(imageInfo2);
        sequence.add(imageInfo1);
        when(imageInfoRepository.getRandomImageInfos(2)).thenReturn(sequence);

        
        List<ImageInfo> returnSequence = imageInfoService.getRandomImageInfos();
        
        verify(imageInfoRepository, times(1)).getRandomImageInfos(2);
        assertEquals(sequence, returnSequence);
    }
}
