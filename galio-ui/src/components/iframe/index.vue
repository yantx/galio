<template>
  <AppPage :show-footer="false">
    <iframe flex-auto frameborder="none" scrolling="auto" :src="externalUrl" />
  </AppPage>
</template>

<script setup>
import { useRoute } from 'vue-router'

const route = useRoute()
const externalUrl = route.meta.externalUrl
</script>
