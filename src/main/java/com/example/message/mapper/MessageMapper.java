package com.example.message.mapper;

import com.example.message.Message;
import com.example.message.MessagePostDto;
import com.example.message.MessageResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    Message messageDtoToMessage(MessagePostDto messagePostDto);

    MessageResponseDto messageToMessageResponseDto(Message Message);
}
