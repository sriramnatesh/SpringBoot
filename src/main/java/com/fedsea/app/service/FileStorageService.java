package com.fedsea.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fedsea.app.exception.FileStorageException;
import com.fedsea.app.property.FileStorageProperties;

@Service
public class FileStorageService {

	private Path fileStorageLocation;

	@Value("${file.upload-dir}")
	private String directory;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
			
	     //   Set<PosixFilePermission> perms = Files.readAttributes(fileStorageLocation,PosixFileAttributes.class).permissions();

			/*
			 * System.out.format("Permissions before: %s%n",
			 * PosixFilePermissions.toString(perms));
			 * 
			 * perms.add(PosixFilePermission.OWNER_WRITE);
			 * perms.add(PosixFilePermission.OWNER_READ);
			 * perms.add(PosixFilePermission.OWNER_EXECUTE);
			 * perms.add(PosixFilePermission.GROUP_WRITE);
			 * perms.add(PosixFilePermission.GROUP_READ);
			 * perms.add(PosixFilePermission.GROUP_EXECUTE);
			 * perms.add(PosixFilePermission.OTHERS_WRITE);
			 * perms.add(PosixFilePermission.OTHERS_READ);
			 * perms.add(PosixFilePermission.OTHERS_EXECUTE);
			 * Files.setPosixFilePermissions(fileStorageLocation, perms);
			 */
	      //  System.out.format("Permissions after:  %s%n",  PosixFilePermissions.toString(perms));
			
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",ex);
		}
	}

	public String storeFile(MultipartFile file, String username, String type, String imageType) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		fileStorageLocation = Paths.get(directory + "/" +imageType + "/" + username +"/"+"coverprofile").toAbsolutePath().normalize();
		fileName = username + "_" + type + "." + fileName.split("\\.")[1];
		try {
						
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			//create file folder if not exists
			//Path files_path=fileStorageLocation;
			
			 if (!Files.exists(fileStorageLocation)) {
				 try {
		                Files.createDirectories(fileStorageLocation);
		            } catch (IOException e) {
		                //fail to create directory
		                e.printStackTrace();
		            }
			 }			
			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return "/" + imageType + "/" + username + "/coverprofile/" + fileName;
		} catch (IOException ex) {
			
			System.out.print(ex);;
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public List<String> storeFiles(List <MultipartFile> StoryImageFiles, Long userId,String username, String type, String imageType) {
			
		fileStorageLocation = Paths.get(directory + "/" + imageType+ "/" + username +"/"+"stories").toAbsolutePath().normalize();		
		 if (!Files.exists(fileStorageLocation)) {
			 try {
	                Files.createDirectories(fileStorageLocation);
	            } catch (IOException e) {
	                //fail to create directory
	                e.printStackTrace();
	            }
		 }
				
		List<String> fileNames = new ArrayList<String>();		
		long ts;
		int filecount=0;
		if(null != StoryImageFiles && StoryImageFiles.size() > 0) {
			for (MultipartFile multipartFile : StoryImageFiles) {
				filecount++;
			//	String fileName = multipartFile.getOriginalFilename();
			//	fileNames.add(fileName);
				//Handle file content - multipartFile.getInputStream()
				
				// Normalize file name
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				//ts = System.currentTimeMillis()/1000;
				ts= Instant.now().toEpochMilli();
				fileName = userId + "_" + type+"_"+filecount+"_" +ts+ "." + fileName.split("\\.")[1]; 
				try {
					// Check if the file's name contains invalid characters
					if (fileName.contains("..")) {
						throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
					}

					// Copy file to the target location (Replacing existing file with the same name)
					Path targetLocation = this.fileStorageLocation.resolve(fileName);
					Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

					//return "/api/" + imageType + "/image/" + fileName;
				//	return "/api/" + imageType + "/" + username + "/coverprofile/" + fileName;
					//fileNames.add("/users/" + username + "/stories/" + fileName);
					fileNames.add(fileName);
				} catch (IOException ex) {	
					System.out.print(ex);;
					throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
				}
			}
		}
		
		return fileNames;
	}

}
