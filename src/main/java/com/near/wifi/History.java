package com.near.wifi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString
public class History {
    private int id;
    private String locateX;
    private String locateY;
    private String searchDate;

    public History(int id, String locateX, String locateY, String searchDate) {
        this.id = id;
        this.locateX = locateX;
        this.locateY = locateY;
        this.searchDate = searchDate;
    }
}
