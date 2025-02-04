/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mongodb.internal.client.model;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class AbstractConstructibleBsonTest {
    @Test
    void unmodifiable() {
        AbstractConstructibleBson<?> constructible = AbstractConstructibleBson.of(new BsonDocument("name", new BsonString("value")));
        String expected = constructible.toBsonDocument().toJson();
        constructible.newAppended("name2", "value2");
        assertEquals(expected, constructible.toBsonDocument().toJson());
    }

    @Test
    void emptyIsImmutable() {
        AbstractConstructibleBson<?> constructible = AbstractConstructibleBson.of(new BsonDocument());
        String expected = constructible.toBsonDocument().toJson();
        constructible.toBsonDocument().append("name2", new BsonString("value2"));
        assertEquals(expected, constructible.toBsonDocument().toJson());
    }
}
