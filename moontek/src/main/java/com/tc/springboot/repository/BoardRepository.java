package com.tc.springboot.repository;
import com.tc.springboot.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BoardRepository  extends JpaRepository<Board, Integer>{
}
