package com.fedsea.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fedsea.app.model.LoggedTime;


@Repository
public interface LoggedTimeRepository extends JpaRepository<LoggedTime, Long>{

		//LoggedTime findByUniqid(String uniqid);
	
	  @Query(value =
	  "SELECT DISTINCT lt.username FROM loggedtime lt WHERE lt.uniqid = ?1 "
	  , nativeQuery = true) List<String> getAllUniqid(String uniqid);
	 
	
	/*
	 * LoggedTime findByLoggedtime(String loggedtime);
	 * 
	 * LoggedTime findByUsername(String username);
	 */
	
	/*
	 * @Query(value =
	 * "SELECT COUNT(*) FROM devicemaster cs WHERE devicetype = ?1 AND deviceid IN (?2)"
	 * , nativeQuery = true) Long findByDevicetypeAndDeviceids(int
	 * devtype,List<String> deviceids);
	 */
}
