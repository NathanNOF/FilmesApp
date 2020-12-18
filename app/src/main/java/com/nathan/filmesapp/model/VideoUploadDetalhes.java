package com.nathan.filmesapp.model;

public class VideoUploadDetalhes  {

    String video_slide,video_type,_video_thum,video_url,video_name,video_descricao,video_categoria;

    public VideoUploadDetalhes(String video_slide, String video_type, String _video_thum, String video_url, String video_name, String video_descricao, String video_categoria) {
        this.video_slide = video_slide;
        this.video_type = video_type;
        this._video_thum = _video_thum;
        this.video_url = video_url;
        this.video_name = video_name;
        this.video_descricao = video_descricao;
        this.video_categoria = video_categoria;
    }

    public VideoUploadDetalhes() {
    }

    public String getVideo_slide() {
        return video_slide;
    }

    public void setVideo_slide(String video_slide) {
        this.video_slide = video_slide;
    }

    public String getVideo_type() {
        return video_type;
    }

    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }

    public String get_video_thum() {
        return _video_thum;
    }

    public void set_video_thum(String _video_thum) {
        this._video_thum = _video_thum;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_descricao() {
        return video_descricao;
    }

    public void setVideo_descricao(String video_descricao) {
        this.video_descricao = video_descricao;
    }

    public String getVideo_categoria() {
        return video_categoria;
    }

    public void setVideo_categoria(String video_categoria) {
        this.video_categoria = video_categoria;
    }
}
