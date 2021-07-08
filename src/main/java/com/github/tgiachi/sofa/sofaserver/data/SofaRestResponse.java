package com.github.tgiachi.sofa.sofaserver.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SofaRestResponse<T> implements Serializable {
    T data;
    SofaRestResponseType status;
    Exception ex;
}
