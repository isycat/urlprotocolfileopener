# README
The intended use of this is to redirect the  and open them in BambuStudio

Example usage: 
In windows registry editor, set the default key at `Computer\HKEY_CLASSES_ROOT\prusaslicer\shell\open\command` to
`"E:\Program Files\UrlProtocolFileOpener.exe" "E:\Program Files\Bambu Studio\bambu-studio.exe" "%1"`

## Todo
* clean up temp files
* replace hard coded "open/?files=" for broader use
