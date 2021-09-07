package com.onegateafrica.entity.pojo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailAddresses {
  private List<String> addresses = new ArrayList<>();
}
