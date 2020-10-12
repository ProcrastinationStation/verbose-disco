package com.example.stockcourt.Models.Utilities

const val BASE_URL = "https://helloworld3-gjhzfsu4ba-uc.a.run.app/api"
const val GET_POSTS = "${BASE_URL}/blog/getPosts"
const val GET_FEATURED = "${BASE_URL}/blog/getFeatured"
const val GET_POST = "${BASE_URL}/blog/getPost?id="
const val GET_REGISTER_TOKEN = "${BASE_URL}/status"
const val REGISTER_USER = "${BASE_URL}/auth/register"
const val LOGIN_USER = "${BASE_URL}/auth/login"
const val GET_USER = "${BASE_URL}/user/getUser"
const val CHANGE_PASS = "${BASE_URL}/user/chpass"
const val CHANGE_NAME = "${BASE_URL}/user/chinfo"
const val GET_PROFILE_IMAGE = "${BASE_URL}/user/getImage?id="