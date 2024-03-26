package com.dame.ecommece.repository;

import com.dame.ecommece.entity.Category;
import com.dame.ecommece.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>  {
}
