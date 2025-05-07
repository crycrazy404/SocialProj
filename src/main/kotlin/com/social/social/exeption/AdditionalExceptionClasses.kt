package com.social.social.exeption


class ResourceNotFoundException(message: String?) : RuntimeException(message)

class ConflictException(message: String?) : RuntimeException(message)

