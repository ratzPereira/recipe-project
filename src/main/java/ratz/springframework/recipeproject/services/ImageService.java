package ratz.springframework.recipeproject.services;


import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Integer recipeId, MultipartFile file);
}
