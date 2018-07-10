package uk.co.datadisk.ddflix.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uk.co.datadisk.ddflix.services.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageServiceImpl implements ImageService {

    private final Path rootLocation = Paths.get("C://java_projects/ddflix/images/classification");

    @Override
    public Byte[] imageToByteConversion(MultipartFile file) {
        System.out.println("Inside convertToImageFile");

        Byte[] byteObjects = null;

        try {
            byteObjects = new Byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            System.out.println("Finished converting file into Byte array");

        } catch (IOException e) {
            //todo handle better
            System.out.println("ERROR: trying to convert file to byte array");
            e.printStackTrace();
        }

        return byteObjects;
    }

    @Override
    public byte[] imageTobyteConversion(Byte[] profileImage) {

        byte[] byteArray = new byte[profileImage.length];
        int i = 0;

        for (Byte wrappedByte : profileImage){
            byteArray[i++] = wrappedByte; //auto unboxing
        }
        return byteArray;
    }

    public String store(MultipartFile file){

        String filename = file.getOriginalFilename();
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }

        return filename;
    }

    public String storeFilmImages(MultipartFile file, Long id, String action){

        String filename = file.getOriginalFilename();
        String firstLetter = filename.substring(0, 1).toUpperCase();
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1);

        String newFileName;

        switch (action){
            case "background1":
                newFileName = id + "bg1." + fileExtension;
                break;
            case "background2":
                newFileName = id + "bg2." + fileExtension;
                break;
            default:
                newFileName = id + "C." + fileExtension;
                break;
        }


        System.out.println("Filename: " + filename + "  First letter: " + firstLetter + "  Extension: " + fileExtension);
        System.out.println("New Filename: " + newFileName);

        Path path = Paths.get("C://java_projects/ddflix/images/film/" + firstLetter);

        if(!Files.exists(path)){
            System.out.println("Create new directory");
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                //something else went wrong
                e.printStackTrace();
            }
        }

        try {
            Files.copy(file.getInputStream(), path.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }

        return newFileName;
    }

    public String storeActorImage(MultipartFile file){

        String filename = file.getOriginalFilename();
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1);

        String firstLetter = filename.substring(0, 1).toUpperCase();

        System.out.println("Filename: " + filename + "  First letter: " + firstLetter + "  Extension: " + fileExtension);

        Path path = Paths.get("C://java_projects/ddflix/images/actor/" + firstLetter);

        if(!Files.exists(path)){
            System.out.println("Create new directory");
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                //something else went wrong
                e.printStackTrace();
            }
        }

        try {
            Files.copy(file.getInputStream(), path.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }

        return filename;
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
            System.out.println("image files directory created");
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}
