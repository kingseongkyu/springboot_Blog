package me.seongkyu.springbootdeveloper.Controller;

import java.security.Principal;

@DisplayName("addArticle: 아티클 추가할 때 title이 null이면 실패한다.")
@Test
public void addArticleNullValidation() throws Exception {
    //given
    final String url = "/api/articles";
    final String title = null;
    final String content = "content";
    final AddArticleRequest userRequest = new AddArticleRequest(title, content);

    final String requestBody = objectMapper.writeValueAsString(userRequest);

    principal principal = Mockito.mock(Principal.class);
    Mockito.when(principal.getName()).thenReturn("username");

    //when
    ResultActions result = mockMvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .principal(principal)
            .content(requestBody));

    //then
    result.andExpect(status().isBadRequest());
}

@DisplayName("addArticle: 아티클 추가할 때 title이 10자를 넘으면 실패한다.")
@Test
public void addArticleSizeValidation() throws Exception {

    //given
    Faker faker = new Faker();

    final String url = "/api/articles";
    final String title = faker.lorem().characters(11);
    final String content = "content";
    final AddArticleRequest userRequest = new AddArticleRequest(title, content);

    final String requestBody = objectMapper.writeValueAsString(userRequest);

    Principal principal = Mockito.mock(principal.class);
    Mockito.when(principal.getName()).thenReturn("username");

    //when
    ResultActions result = mockMvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .principal(principal)
            .content(requestBody));

    //then
    result.andExpect(status().isBadRequest());

}













