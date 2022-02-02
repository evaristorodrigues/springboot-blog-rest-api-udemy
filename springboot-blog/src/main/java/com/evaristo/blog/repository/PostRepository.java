/**
 * 
 */
package com.evaristo.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaristo.blog.entity.Post;

/**
 * @author evari
 *
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	//

}
