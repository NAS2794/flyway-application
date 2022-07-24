package com.flwaway.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemErrorResponse {

    private int status;
    private String message;
    private  long timeStamp;

    private List<String> fieldErrors = new ArrayList<>();


}