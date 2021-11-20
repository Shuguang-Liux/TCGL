package com.tcgl.common.model.constant;

public enum TokenPrefix {

        /**
         * jwt token相关信息
         */
        JWT_TOKEN("Authorization", "Bearer ", "login_user:");

        private final String header;
        private final String tokenPrefix;
        private final String redisPrefix;

        TokenPrefix(String header, String tokenPrefix, String redisPrefix) {
            this.header = header;
            this.tokenPrefix = tokenPrefix;
            this.redisPrefix = redisPrefix;
        }


        public String header() {
            return this.header;
        }
        public String tokenPrefix() {
            return this.tokenPrefix;
        }
        public String redisPrefix() {
            return this.redisPrefix;
        }

        public static TokenPrefix getPrefixByHeader(String header) {
            TokenPrefix[] values = TokenPrefix.values();
            TokenPrefix result = null;
            for (TokenPrefix v: values) {
                if (header.equals(v.header())) {
                    result = v;
                    break;
                }
            }
            return result;
        }
    }