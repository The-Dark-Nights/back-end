package com.darknights.devigation.domain.category.command.domain.aggregate.entity.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Classification {
    CLASS1("Classification_CLASS1", "CLASS1"),
    CLASS2("Classification_CLASS2", "CLASS2"),
    CLASS3("Classification_CLASS3", "CLASS3"),
    CLASS4("Classification_CLASS4", "CLASS4"),
    CLASS5("Classification_CLASS5", "CLASS5"),
    CLASS6("Classification_CLASS6", "CLASS6");

    private final String key;
    private final String title;
}
