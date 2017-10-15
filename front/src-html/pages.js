const DEFAULT_CHUNKS = ['app'];

const pages = [
  {
    path: '/',
    foo: {
      bar: 'drei',
      baz: ']]>&quot;\n\'</script>',
    },
  },
];

module.exports = pages.map(p => {
  if (!p.chunks) {
    p.chunks = DEFAULT_CHUNKS;
  }
  return p;
});
