package com.social.social.user.controller

import com.social.social.user.dto.UserInputDto
import com.social.social.user.dto.UserOutputDto
import com.social.social.user.service.impl.UserServiceImpl
import io.swagger.v3.oas.annotations.Operation
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/users"], produces = ["application/json"])
class UserController(
    val userService: UserServiceImpl,
) {

    @GetMapping("/all")
    @Operation(summary = "Получить всех пользователей")
    fun getAll(): List<UserOutputDto>{
        return userService.findAll()
    }

    @GetMapping("/getById/{id}")
    @Operation(summary = "Получить пользователя по ID")
    fun getById(@PathVariable id: Long): UserOutputDto =
        userService.findById(id)?.run {
            this
        }?: throw UsernameNotFoundException("User with id $id not found")

    @GetMapping("/getByUsername/{username}")
    @Operation(summary = "Получить пользователя по Username")
    fun getByUsername(@PathVariable username: String): UserOutputDto =
        userService.findByUsername(username)?.run {
            this
        }?: throw UsernameNotFoundException("User with name $username not found")

    @PostMapping("/create")
    @Operation(summary = "Создать нового пользователя")
    fun create(@RequestBody user: UserInputDto): UserOutputDto {
        return userService.create(user)
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Обновить существующего пользователя")
    fun update(@PathVariable id: Long, @RequestBody user: UserInputDto): UserOutputDto {
        return userService.update(id, user)
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить пользователя")
    fun deleteById(@PathVariable id: Long): Int{
        return userService.delete(id)
    }

}