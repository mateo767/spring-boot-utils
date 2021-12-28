package com.larchsoftware.utils.springboot.integration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SomeObject implements Serializable {
    @Serial
    private static final long serialVersionUID = 8545049709284423835L;

    private String sField;
    private int iField;
}