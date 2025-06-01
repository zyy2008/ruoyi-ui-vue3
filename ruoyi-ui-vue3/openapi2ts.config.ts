export default [
  {
    requestLibPath: "import request from '@/utils/http'",
    serversPath: "./src/services/",
    projectName: "api1",
    apiPrefix: '"/dev-api"',
    schemaPath: "http://39.100.43.147:11004/v3/api-docs",
    mock: false,
    hook: {
      customFunctionName: (api: { path: string; method: string }) => {
        const { path, method } = api;
        const str = path
          .match(/[a-zA-Z]+/g)
          ?.map((str) => str.charAt(0).toUpperCase() + str.slice(1));
        return `${method}${str?.join("")}`;
      },
    },
  },
];
