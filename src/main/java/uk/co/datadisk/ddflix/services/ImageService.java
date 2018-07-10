package uk.co.datadisk.ddflix.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Byte[] imageToByteConversion(MultipartFile file);

    byte[] imageTobyteConversion(Byte[] profileImage);

    String store(MultipartFile file);

    String storeFilmImages(MultipartFile file, Long id, String action);
    String storeActorImage(MultipartFile file, String firstLetter);

    void init();
}
