/*
 * Copyright (c) 2024. Kaloyan Doychinov
 */

package tech.kaloyan.snackoverflow.resources.resp;

import lombok.Data;

@Data
public class UserResp {
    private String id;
    private String username;
    private String email;
}
