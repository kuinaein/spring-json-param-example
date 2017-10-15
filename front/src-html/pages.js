const DEFAULT_CHUNKS = ['app'];

const pages = [
  {
    path: '/',
    foo: {
      bar: 'ein',
      baz: ']]>&quot;\n\'</script>',
    },
  },
  {
    path: '/a/',
    chunks: ['app2'],
    foo: {
      bar: 'zwei',
      baz: ']]>&quot;\n\"\'</script>',
    },
  },
  {
    path: '/a/create',
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
