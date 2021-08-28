package br.com.devcave.parameterstore.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RefreshScope
@RestController
@RequestMapping("tests")
class TestController(
    @Value("\${parameter-store.sample}")
    private val sampleParameter: String
) {

    @GetMapping
    fun getTest(): String {
        return sampleParameter;
    }
}