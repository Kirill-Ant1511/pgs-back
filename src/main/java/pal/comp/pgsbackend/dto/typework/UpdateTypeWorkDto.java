package pal.comp.pgsbackend.dto.typework;

public record UpdateTypeWorkDto(
        String code,
        String name
) {
    @Override
    public String toString() {
        return "Code: " + this.code + " Name: " + this.name;
    }
}
