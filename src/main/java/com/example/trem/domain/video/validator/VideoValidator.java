package com.example.trem.domain.video.validator;

import com.example.trem.domain.shared.IValidator;
import com.example.trem.domain.video.entity.IVideo;
import com.example.trem.domain.video.exception.InvalidFileNameVideoException;
import com.example.trem.domain.video.exception.InvalidIdVideoException;
import com.example.trem.domain.video.exception.VideoException;

public class VideoValidator implements IValidator<IVideo> {

  private void validateId(IVideo video) throws VideoException {
    if (video.getId() == null || video.getId().toString().isEmpty()) {
      throw new InvalidIdVideoException("Id is required");
    }
  }

  private void validateFileName(IVideo video) throws VideoException {
    if (video.getFileName() == null || video.getFileName().isEmpty()) {
      throw new InvalidFileNameVideoException("File name is required");
    }
  }

  @Override
  public void validate(IVideo video) throws RuntimeException {
    validateId(video);
    validateFileName(video);
  }

}
