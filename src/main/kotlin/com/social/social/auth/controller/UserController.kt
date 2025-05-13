package com.social.social.auth.controller

import com.social.social.auth.dto.UserRegistrationRequest
import com.social.social.auth.dto.UserInfoResponse
import com.social.social.auth.dto.UserUpdateRequest
import com.social.social.auth.service.user.impl.UserServiceImpl
import io.swagger.v3.oas.annotations.Operation
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping(value = ["/api/v1/users"], produces = ["application/json"])
class UserController(
    val userService: UserServiceImpl,
) {

    @GetMapping("/all")
    @Operation(summary = "Получить всех пользователей")
    fun getAll(): List<UserInfoResponse>{
        return userService.findAll()
    }

    @GetMapping("/getById/{id}")
    @Operation(summary = "Получить пользователя по ID")
    fun getById(@PathVariable id: Long): UserInfoResponse =
        userService.findById(id)

    @GetMapping("/getByUsername/{username}")
    @Operation(summary = "Получить пользователя по Username")
    fun getByUsername(@PathVariable username: String): UserInfoResponse =
        userService.findByUsername(username)


    @PostMapping("/create")
    @Operation(summary = "Создать нового пользователя")
    fun create(@RequestBody user: UserRegistrationRequest): UserInfoResponse {
        return userService.create(user)
    }
    @PutMapping("/update/{id}")
    @Operation(summary = "Обновить существующего пользователя")
    fun update(@PathVariable id: Long, @RequestBody user: UserUpdateRequest): UserInfoResponse {
        return userService.update(id, user)
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить пользователя")
    fun deleteById(@PathVariable id: Long): Int{
        return userService.delete(id)

    }

}