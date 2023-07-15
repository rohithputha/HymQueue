package org.hitro.model.iodtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class HymOutput<T,Class>{
    T data;
    Class dataType;

}
