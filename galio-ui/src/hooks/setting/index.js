import { warn } from '@/utils/log'
import { getAppEnvConfig } from '@/utils/env'

export const useGlobSetting = () => {
  const {
    VITE_APP_TITLE,
    VITE_API_URL,
    VITE_APP_SHORT_NAME,
    VITE_API_URL_PREFIX,
    VITE_UPLOAD_URL,
    VITE_PROD_MOCK,
    VITE_IMG_URL,
  } = getAppEnvConfig()

  if (!/[a-zA-Z\_]*/.test(VITE_APP_SHORT_NAME)) {
    warn(
      `VITE_APP_SHORT_NAME Variables can only be characters/underscores, please modify in the environment variables and re-running.`,
    )
  }

  // Take global configuration
  const glob = {
    title: VITE_APP_TITLE,
    apiUrl: VITE_API_URL,
    shortName: VITE_APP_SHORT_NAME,
    urlPrefix: VITE_API_URL_PREFIX,
    uploadUrl: VITE_UPLOAD_URL,
    prodMock: VITE_PROD_MOCK,
    imgUrl: VITE_IMG_URL,
  }
  return glob
}
