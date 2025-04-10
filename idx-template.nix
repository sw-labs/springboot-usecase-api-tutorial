{ pkgs, ... }: {
  bootstrap = ''
    cp -rf ${./.} "$WS_NAME"
    rm "$WS_NAME"/idx-template.json
    rm "$WS_NAME"/idx-template.nix
    chmod -R +w "$WS_NAME"
    mv "$WS_NAME" "$out"
  '';
}