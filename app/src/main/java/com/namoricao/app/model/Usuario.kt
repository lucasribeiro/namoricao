package com.namoricao.app.model

data class Usuario(val email: String, val senha: String)

data class Usuarios(val usuarios: List<Usuario>)
