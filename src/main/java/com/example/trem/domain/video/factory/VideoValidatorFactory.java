package com.example.trem.domain.video.factory;

import com.example.trem.domain.shared.IValidator;
import com.example.trem.domain.video.entity.IVideo;
import com.example.trem.domain.video.validator.VideoValidator;

public class VideoValidatorFactory {

  public static IValidator<IVideo> create() {
    return new VideoValidator();
  }

}
