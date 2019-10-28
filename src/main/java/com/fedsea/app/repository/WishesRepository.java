package com.fedsea.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fedsea.app.model.Chat;
import com.fedsea.app.model.Wishes;

public interface WishesRepository extends JpaRepository<Wishes, Long> {

	List<Wishes> findByWishesby(String username);
	
	List<Wishes> findByUsername(String username);

	//List<Wishes> findByUsernameAndWishesby(String username,String wishesby );
	List<Wishes> findByUsernameAndRelationtoAndWishesby(String username,String relationto,String wishesby );
	
	@Transactional
	long deleteByIdAndUsernameAndRelationtoAndWishesby(@Param("id") Long id,@Param("username") String username, 
		    @Param("relationto") String relationto,@Param("wishesby") String wishesby);
	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query(value="DELETE FROM wishes WHERE username=?1 and wishesby=?2 and
	 * relationto <=?3, nativeQuery = true)
	 */
}
