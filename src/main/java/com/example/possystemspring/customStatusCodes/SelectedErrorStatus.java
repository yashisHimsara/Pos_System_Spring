package com.example.possystemspring.customStatusCodes;

import com.example.possystemspring.dto.CustomerStatus;
import com.example.possystemspring.dto.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements CustomerStatus, ItemStatus {
    private int statusCode;
    private String statusMessage;
}
