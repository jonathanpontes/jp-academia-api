package com.academia.adapters.repository.jpainterfaces;

import com.academia.adapters.repository.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileJpaRepository extends JpaRepository<FileEntity, String> {
}
