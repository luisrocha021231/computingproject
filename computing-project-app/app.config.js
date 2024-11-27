import "dotenv/config";

export default ({ config }) => ({
    ...config,
    extra: {
        SpringBootApiRestUrl: process.env.SPRINGBOOT_API_REST_URL,
        SpringBootApiRestUrlLatest: process.env.SPRINGBOOT_API_REST_URL_LATEST,
        SpringBootApiRestUrlOvenState: process.env.SPRINGBOOT_API_REST_URL_OVEN_STATE,
        SpringBootApiRestUrlMax: process.env.SPRINGBOOT_API_REST_URL_MAX,
        SpringBootApiRestUrlMin: process.env.SPRINGBOOT_API_REST_URL_MIN,
        "eas" : {
            "projectId" : "e3165eb6-e25b-4131-824b-fd45ae2c64a0"
        },
        "owner" : "rochaluis021231",
    } 
});
