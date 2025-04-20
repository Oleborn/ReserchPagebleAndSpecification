package oleborn.pagefilterdemo.model;

import lombok.Builder;

@Builder
public record PersonDto(

        Integer numberPassport,
        String name,
        String surname,
        int age,
        String sex
) {
}
