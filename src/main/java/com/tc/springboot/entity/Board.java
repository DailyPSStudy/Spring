package com.tc.springboot.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name="tc_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int board_num;
    private String board_title;
    private String board_content;
    private int board_tcnum;
    private int board_commentnum;
}
